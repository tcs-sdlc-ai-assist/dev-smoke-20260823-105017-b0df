import {test, expect} from '@playwright/test';

test('customer blocks a card through the live backend', async ({page}) => {
  await page.goto('/');
  await page.getByRole('button', {name: /sign in/i}).click();
  await page.getByRole('button', {name: 'Cards'}).click();
  await page.getByRole('button', {name: /block card/i}).click();
  await expect(page.getByText('BLOCKED')).toBeVisible();
});

test('customer can raise a support ticket and see it', async ({page}) => {
  await page.goto('/');
  await page.getByRole('button', {name: /sign in/i}).click();
  await page.getByRole('button', {name: 'Support'}).click();
  await page.getByLabel(/subject/i).fill('Card question');
  await page.getByLabel(/message/i).fill('Please call me.');
  await page.getByRole('button', {name: /send message/i}).click();
  await expect(page.getByText('Card question')).toBeVisible();
});
