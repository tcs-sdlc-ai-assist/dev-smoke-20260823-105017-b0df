import {test, expect} from '@playwright/test';

test('customer opens beneficiary management over live APIs', async ({page}) => {
  await page.goto('/');
  await page.getByRole('button', {name: /sign in/i}).click();
  await page.getByRole('button', {name: 'Beneficiaries'}).click();
  await expect(page.getByText('Add beneficiary')).toBeVisible();
  await page.getByLabel('Beneficiary name').fill('Jordan Lee');
  await page.getByLabel('Account number').fill('887766');
  await page.getByLabel('Bank').fill('ANSH');
  await page.getByRole('button', {name: /add recipient/i}).click();
  await expect(page.getByText('Jordan Lee')).toBeVisible();
});
