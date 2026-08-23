import {test, expect} from '@playwright/test';

test('customer signs in through the live API and sees account data', async ({page}) => {
  const requests: string[] = [];
  page.on('request', request => { if (request.url().includes('/api/')) requests.push(request.url()); });
  await page.goto('/');
  await page.getByRole('button', {name: /sign in/i}).click();
  await expect(page.getByText('Everyday account')).toBeVisible();
  expect(requests.some(url => url.includes('/api/auth/login'))).toBeTruthy();
  expect(requests.some(url => url.includes('/api/accounts'))).toBeTruthy();
});
